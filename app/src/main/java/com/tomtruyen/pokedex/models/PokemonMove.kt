package com.tomtruyen.pokedex.models

import com.google.gson.annotations.SerializedName

data class PokemonMove(
    val move: Move,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<VersionGroupDetails>
)

data class Move(
    val name: String,
    val url: String,
)

data class VersionGroupDetails(
    @SerializedName("level_learned_at")
    val levelLearnedAt: Int,
    @SerializedName("move_learn_method")
    val moveLearnMethod: LearnMethod
)

data class LearnMethod(
    val name: String
)
